"""Builds app/src/main/assets/index.html from web/app.src.html.

The source is the same page as the web demo. This script inlines the images,
swaps Google Fonts for the bundled Montserrat files so the app works offline,
and wraps it in a full HTML document for the Android WebView.

Run from the repo root:  python3 web/build.py
"""
import json, pathlib

root = pathlib.Path(__file__).resolve().parent.parent
src = (root / 'web' / 'app.src.html').read_text(encoding='utf-8')
imgs = json.loads((root / 'web' / 'imgs.json').read_text(encoding='utf-8'))

google = '<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800&display=swap">'
assert google in src, 'font link not found'
faces = ''.join(
    f'@font-face{{font-family:"Montserrat";font-weight:{w};font-style:normal;font-display:block;src:url("fonts/montserrat-{f}.woff2") format("woff2")}}'
    for w, f in [(400, 400), (500, 500), (600, 600), (700, 700), (800, 700)]
)
body = src.replace(google, f'<style>{faces}</style>').replace('%%IMGS%%', json.dumps(imgs))

page = f"""<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, viewport-fit=cover">
<style>html,body{{height:100%;margin:0;-webkit-tap-highlight-color:transparent;-webkit-user-select:none;user-select:none}}input,textarea{{-webkit-user-select:text;user-select:text}}[hidden]{{display:none!important}}</style>
</head>
<body>
{body}
</body>
</html>
"""
out = root / 'app' / 'src' / 'main' / 'assets' / 'index.html'
out.write_text(page, encoding='utf-8')
print(f'wrote {out.relative_to(root)} ({len(page):,} bytes)')
