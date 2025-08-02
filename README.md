# capacitor-apk-updater

Allows installation of apk files from remote url.

## Install

```bash
npm install capacitor-apk-updater
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`downloadAndInstall(...)`](#downloadandinstall)
* [`checkPermissions()`](#checkpermissions)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


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
