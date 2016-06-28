(function(global) {

    var map = {
        'app':                        'app',
        '@angular':                   'node_modules/@angular',
        'router':                     'node_modules/@angular/router',
        'angular2-in-memory-web-api': 'node_modules/angular2-in-memory-web-api',
        'rxjs':                       'node_modules/rxjs'
    };

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
        'forms',
        'platform-browser-dynamic',
        'platform-browser',
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
