browsers: ['ChromeHeadless'],
customLaunchers: {
    ChromeHeadless: {
        base: 'Chrome',
        flags: ['--headless', '--disable-gpu', '--no-sandbox', '--disable-setuid-sandbox', '--remote-debugging-port=9222']
    }
},
