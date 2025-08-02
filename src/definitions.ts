export interface ApkUpdaterPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
