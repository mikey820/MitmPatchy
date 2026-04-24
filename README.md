# MitmPatchy

An Android app that lists installed applications and helps you patch them using [apk-mitm](https://github.com/niklashigi/apk-mitm) to disable certificate pinning for HTTPS traffic interception.

## Features
- Lists all installed apps on your device
- One-tap selection to patch an app
- Shows detailed patching progress logs
- Prompts to uninstall the original app before installing the patched version
- Supports installing the patched APK directly

## How it works
1. Open MitmPatchy
2. Select the app you want to patch
3. Confirm the patch operation
4. Watch the logs as the patching progresses
5. Uninstall the original app when prompted
6. Install the patched APK

## Building
This project uses GitHub Actions to automatically build and release the APK on every push to `main`.

## License
MIT
