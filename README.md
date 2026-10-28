# FIO Wallet Prototype

A clickable Android demo of a FIO wallet, built for showing at events. It runs fully offline with demo balances and no real funds.

**Download the latest APK:** [FIO-Wallet-Prototype.apk](../../releases/latest/download/FIO-Wallet-Prototype.apk)

## What's in the demo

- Animated splash: a wallet address slides into a liquid drop and comes back out as `yourname@web3`
- Onboarding: claim a free handle, secure the wallet, link chains, or connect with MetaMask
- Wallet home with demo balances, send and request by handle, receive with QR
- Transaction confirmation with fee speed, then signing and broadcast steps
- FIO Requests: pay or decline an incoming request
- FIO Domains: register a domain, issue handles on it, make it public or private, wrap to Polygon, renew and transfer
- Settings: handles, linked chains, FIO network and roadmap, FIO-enabled wallets, reset demo

## How it's built

- `web/app.src.html` is the whole app: one HTML page with its CSS and JavaScript.
- `web/build.py` turns it into `app/src/main/assets/index.html`, inlining the images and using the bundled Montserrat fonts so nothing loads from the internet.
- `app/` is a small Kotlin Android app with a full-screen WebView that loads that page. The hardware back button goes to the previous screen.
- `.github/workflows/build-apk.yml` builds the APK on every push to `main` and publishes it as the latest release.

## Change the demo

1. Edit `web/app.src.html`.
2. Run `python3 web/build.py`.
3. Commit and push. GitHub Actions builds a new APK in a few minutes.

## Install on a phone

Open the latest release on your Android phone, download `FIO-Wallet-Prototype.apk`, and open it. Android may ask you to allow installs from your browser or file manager the first time.

Tip: open Settings › Reset demo inside the app before each new visitor.

Fonts: Montserrat, SIL Open Font License (`app/src/main/assets/fonts/OFL.txt`). MetaMask fox rendered from MetaMask's ISC-licensed `@metamask/logo` package.
