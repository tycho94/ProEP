(function(global) {

    var map = {
        'app':                        'app', // 'dist',
        '@angular':                   '/node_modules/@angular',
        'angular2-in-memory-web-api': '/node_modules/angular2-in-memory-web-api',
    };

    // packages tells the System loader how to load when no filename and/or no extension
    var packages = {
        'app': {
            main: 'bootstrap.js',
            defaultExtension: 'js'
        },
        'angular2-in-memory-web-api': {
            main: 'index.js',
        },
    };

    var packageNames = [
        'common',
        'compiler',
        'core',
        'http',
        'platform-browser',
        'platform-browser-dynamic',
        'router',
        'router-deprecated',
        'upgrade',
    ];

    // Individual files (~300 requests):
    packageNames.forEach(function (name) {
        packages['@angular/' + name] = { main: 'index.js', defaultExtension: 'js' };
    });

    packages['@angular/common'].main = 'common.umd.js';
    packages['@angular/core'].main = 'core.umd.js';
    packages['@angular/compiler'].main = 'compiler.umd.js';
    packages['@angular/http'].main = 'http.umd.js';
    packages['@angular/platform-browser'].main = 'platform-browser.umd.js';
    packages['@angular/platform-browser-dynamic'].main = 'platform-browser-dynamic.umd.js';

    var config = {
        paths: {
            "rxjs/*": "node_modules/rxjs/bundles/Rx.umd.min.js"
        },
        map: map,
        packages: packages,
    }

    System.config(config);

})(this);
