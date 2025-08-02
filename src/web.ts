import { WebPlugin } from '@capacitor/core';

import type { ApkUpdaterPlugin } from './definitions';

export class ApkUpdaterWeb extends WebPlugin implements ApkUpdaterPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
