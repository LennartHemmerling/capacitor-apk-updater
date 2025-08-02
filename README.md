# capacitor-apk-updater

Allows installation of apk files from remote url.

## Install

```bash
npm install capacitor-apk-updater
npx cap sync
```

## API

<docgen-index>

* [`downloadAndInstall(...)`](#downloadandinstall)
* [`checkPermissions()`](#checkpermissions)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### downloadAndInstall(...)

```typescript
downloadAndInstall(options: { url: string; }) => Promise<void>
```

| Param         | Type                          |
| ------------- | ----------------------------- |
| **`options`** | <code>{ url: string; }</code> |

--------------------


### checkPermissions()

```typescript
checkPermissions() => Promise<{ granted: boolean; rejected: boolean; internet: boolean; install: boolean; }>
```

**Returns:** <code>Promise&lt;{ granted: boolean; rejected: boolean; internet: boolean; install: boolean; }&gt;</code>

--------------------

</docgen-api>
