'use strict';

module.exports = function (grunt) {
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),

		project: {
			dist: ['app'],
			base: ['src'],
			jquery: ['node_modules/jquery'],
			bootstrap: ['node_modules/bootstrap-sass/assets']
		},

		sass: {
			dev: {
				files: [{
					expand: true,
					cwd: './<%= project.base %>/sass',
					src: ['**/*.scss'],
					dest: './<%= project.base %>/css',
					ext: '.css'
				}]
			}
		},

		scsslint: {
			files: [
				'./<%= project.base %>/sass/**/*.scss',
			],

			options: {
				colorizeOutput: true
			}
		},

		jscs: {
			src: ['<%= project.base %>/js/*.js', '!<%= project.base %>/js/*.min.js'],

			options: {
				requireCurlyBraces: ['if']
			}
		},

		cssmin: {
			all: {
				files: [{
					expand: true,
					cwd: './<%= project.base %>/css',
					src: ['*.css', '!*.min.css'],
					dest: './<%= project.base %>/css',
					ext: '.min.css'
				}]
			}
		},

		uglify: {
			options: {
				mangle: {
					except: ['jQuery']
				}
			},

			all: {
				files: [{
					expand: true,
					cwd: '<%= project.base %>/js',
					src: ['*.js', '!*.min.js'],
					dest: '<%= project.base %>/js',
					ext: '.min.js'
				}]
			}
		},

		sync: {
			main: {
				files: [
					{
						expand: true,
						cwd: '.',
						src: ['*.html'],
						dest: './<%= project.dist %>/',
						filter: 'isFile'
					},

					{
						expand: true,
						cwd: '<%= project.base %>/img',
						src: ['**/*', '!**/*.tiff'],
						dest: '<%= project.dist %>/img' 
					},

					{
						expand: true,
						cwd: '<%= project.base %>/css',
						src: ['**/*.css'],
						dest: '<%= project.dist %>/css' 
					},

					{
						expand: true,
						cwd: '<%= project.base %>/js',
						src: ['**/*.js'],
						dest: '<%= project.dist %>/js' 
					},

					{
						expand: false,
						cwd: '<%= project.jquery %>/dist',
						src: ['*'],
						dest: '<%= project.dist %>/lib/jquery',
						filter: 'isFile'
					},

					{
						expand: true,
						cwd: '<%= project.bootstrap %>/fonts/bootstrap', 
						src: ['*'],
						dest: '<%= project.dist %>/css/fonts' 
					},

					{
						expand: true,
						cwd: '<%= project.bootstrap %>/javascripts', 
						src: ['*'],
						dest: '<%= project.dist %>/lib/bootstrap',
						filter: 'isFile'
					}
				],

				verbose: true,
				updateAndDelete: true
			}
		},

		copy: {
			all: {
				files: [
					{
						expand: true,
						cwd: '.',
						src: ['*.html'],
						dest: './<%= project.dist %>/',
						filter: 'isFile'
					},

					{
						expand: true,
						cwd: './<%= project.base %>/img',
						src: ['**/*', '!**/*.tiff'],
						dest: './<%= project.dist %>/img' 
					},

					{
						expand: true,
						cwd: './<%= project.base %>/css',
						src: ['**/*.css'],
						dest: './<%= project.dist %>/css' 
					},

					{
						expand: true,
						cwd: './<%= project.base %>/js',
						src: ['**/*.js'],
						dest: '<%= project.dist %>/js' 
					},

					{
						expand: true,
						cwd: './<%= project.jquery %>/dist',
						src: ['*'],
						dest: './<%= project.dist %>/lib/jquery',
						filter: 'isFile'
					},

					{
						expand: true,
						cwd: './<%= project.bootstrap %>/fonts/bootstrap', 
						src: ['*'],
						dest: './<%= project.dist %>/css/fonts' 
					},

					{
						expand: true,
						cwd: './<%= project.bootstrap %>/javascripts', 
						src: ['*'],
						dest: './<%= project.dist %>/lib/bootstrap',
						filter: 'isFile'
					}
				]
			}
		},

		includes: {
			build: {
				cwd: '.',
				src: ['*.html'],
				dest: './<%= project.dist %>',

				options: {
					flatten: true,
					includePath: './<%= project.base %>/include'
				}
			}
		},

		clean: {
			build: {
				src: ['./<%= project.dist %>']
			}
		},

		watch: {
			sass: {
				files: './<%= project.base %>/sass/**/*.scss',
				tasks: ['sass:dev', 'cssmin:all', 'sync']
			},

			script: {
				files: ['./<%= project.base %>/js/**/*.js', '!./<%= project.base %>/js/**/*.min.js'],
				tasks: ['uglify:all', 'sync']
			},

			html: {
				files: ['./*.html'],
				tasks: ['sync', 'includes']
			}
		}
	});

	grunt.event.on('watch', function(action, filepath) {
		grunt.config('sync.main.files', filepath);
	});

	grunt.loadNpmTasks('grunt-sync');
	grunt.loadNpmTasks('grunt-includes');
	grunt.loadNpmTasks('grunt-contrib-clean');
	grunt.loadNpmTasks('grunt-contrib-cssmin');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-sass');
	grunt.loadNpmTasks('grunt-contrib-watch');
	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-browser-sync');
	grunt.loadNpmTasks('grunt-scss-lint');
	grunt.loadNpmTasks('grunt-jscs');

    grunt.registerTask('default', [
        'watch'
    ]);

    grunt.registerTask('build', [
		'clean:build', 'uglify:all', 'sass:dev', 'cssmin:all', 'copy:all', 'includes'
    ]);

    grunt.registerTask('test', [
        'scsslint', 'jscs'
    ]);
};