import { WebPlugin } from '@capacitor/core';

import type { ApkUpdaterPlugin } from './definitions';

export class ApkUpdaterWeb extends WebPlugin implements ApkUpdaterPlugin {
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
