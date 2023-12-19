// Karma configuration file, see link for more information
// https://karma-runner.github.io/1.0/config/configuration-file.html

module.exports = function (config) {
    config.set({
      basePath: '',
      frameworks: ['jasmine', '@angular-devkit/build-angular'],
      plugins: [
        require('karma-jasmine'),
        require('karma-chrome-launcher'),
        require('karma-jasmine-html-reporter'),
        require('@angular-devkit/build-angular/plugins/karma'),
      ],
      client: {
        clearContext: false,
      },
      port: 9876,
      colors: true,
      logLevel: config.LOG_INFO,
      autoWatch: true,
      browsers: ['ChromeHeadless'],
      customLaunchers: {
        ChromeHeadless: {
          base: 'Chrome',
          flags: [
            '--no-sandbox',
            '--disable-gpu',
            '--disable-setuid-sandbox',
            '--headless',
            '--remote-debugging-port=9222',
            '--enable-logging',
            '--user-data-dir=./karma-chrome',
            '--v=1',
            '--disable-background-timer-throttling',
            '--disable-renderer-backgrounding',
            '--proxy-bypass-list=*',
            "--proxy-server='direct://'",
          ],
        },
      },
      singleRun: false,
      restartOnFileChange: true,
    });
  };
  