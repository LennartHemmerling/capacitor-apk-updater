export interface ApkUpdaterPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;

  downloadAndInstall(options: { url: string }): Promise<void>;

  checkPermissions(): Promise<{
    granted: boolean;
    rejected: boolean;
    internet: boolean;
    install: boolean;
  }>;
}

declare global {
  interface PluginRegistry {
    ApkUpdater: ApkUpdaterPlugin;
  }
}
