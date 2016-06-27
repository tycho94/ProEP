(function(global) {

    var map = {
        'app':                        'app', // 'dist',
        '@angular':                   'node_modules/@angular',
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
        }
    };

    var packageNames = [
        'common',
        'compiler',
        'core',
		'forms',
        'http',
        'platform-browser',
        'platform-browser-dynamic',
        'router',
        'router-deprecated',
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
