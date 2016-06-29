var gulp = require('gulp');

var src = 'src/';
var out = 'app/';

/* JS & TS */
var typescript = require('gulp-typescript');
var sourcemaps = require('gulp-sourcemaps');
var tsconf = typescript.createProject('tsconfig.json');

gulp.task('build-ts', function () {
    return gulp.src(src + '**/*.ts')
        .pipe(sourcemaps.init({'identityMap': true, 'debug': true}))
            .pipe(typescript(tsconf))
        .pipe(sourcemaps.write({'addComment': true, 'includeContent': true, 'debug': true}))
        .pipe(gulp.dest(out));
});

gulp.task('bundle-ts', ['build-ts'], function() {
    var path = require('path');
    var Builder = require('systemjs-builder');

    var builder = new Builder('', 'systemjs.config.js');

    console.log('... Build starting ... ');
    builder
        .buildStatic('app/bootstrap.js', 'app/bundle.js')
        .then(function() {
            console.log('--- Build complete ---');
        })
        .catch(function(err) {
            console.log('!!! Build error !!!');
            console.log(err);
        });
});

gulp.task('watch', function () {
    gulp.watch([src + '**/*.ts', 'gulpfile.js'], ['build-ts', 'bundle-ts']);
});

gulp.task('default', ['watch', 'build-ts', 'bundle-ts']);
