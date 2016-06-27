var gulp = require('gulp');

var src = 'src/';
var out = 'app/';

/* JS & TS */
var typescript = require('gulp-typescript');
var tsProject = typescript.createProject('tsconfig.json');

gulp.task('build-ts', function () {
    return gulp.src(src + '**/*.ts')
        .pipe(typescript(tsProject))
        .pipe(gulp.dest(out));
});

gulp.task('bundle-ts', ['build-ts'], function() {
    var path = require('path');
    var Builder = require('systemjs-builder');

    var builder = new Builder('', 'systemjs.config.js');

    builder
        .buildStatic('app/bootstrap.js', 'app/bundle.js', { minify: true })
        .then(function() {
            console.log('Build complete');
        })
        .catch(function(err) {
            console.log('Build error');
            console.log(err);
        });
});

gulp.task('watch', function () {
    gulp.watch(src + '**/*.ts', ['build-ts', 'bundle-ts']);
});

gulp.task('default', ['watch', 'build-ts', 'bundle-ts']);
