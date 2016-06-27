(function(global) {

    var map = {
        'app':                        'app', // 'dist',
        '@angular':                   'node_modules/@angular',
        'router':                     'node_modules/@angular/router',
        'angular2-in-memory-web-api': 'node_modules/angular2-in-memory-web-api',
        'rxjs':                       'node_modules/rxjs'
    };

    // packages tells the System loader how to load when no filename and/or no extension
    var packages = {
        'app': {
            main: 'bootstrap.js',
            defaultExtension: 'js'
        },
		'rxjs': {
            defaultExtension: 'js'
		},
        'angular2-in-memory-web-api': {
            main: 'index.js',
            defaultExtension: 'js'
        },
        'router': {
            main: 'index.js',
            defaultExtension: 'js'
        },
    };

    var packageNames = [
        'common',
        'compiler',
        'core',
        'http',
        'platform-browser',
        'platform-browser-dynamic',
        'upgrade',
    ];

    packageNames.forEach(function (name) {
        packages['@angular/' + name] = {
			main: 'bundles/' + name + '.umd.js',
			defaultExtension: 'js'
		};
    });

    var config = {
        map: map,
        packages: packages,
        defaultJSExtensions: true
    }

    System.config(config);

})(this);
