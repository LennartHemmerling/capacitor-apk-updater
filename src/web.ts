import { WebPlugin } from '@capacitor/core';

import type { ApkUpdaterPlugin } from './definitions';

export class ApkUpdaterWeb extends WebPlugin implements ApkUpdaterPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async downloadAndInstall(): Promise<void> {
    console.log('NOT IMPLEMENTED ON WEB');
  }

  async checkPermissions(): Promise<{
    granted: boolean;
    rejected: boolean;
    internet: boolean;
    install: boolean;
  }> {
    console.log('NOT IMPLEMENTED ON WEB');

    return {
      granted: false,
      rejected: false,
      internet: false,
      install: false,
    };
  }
}
