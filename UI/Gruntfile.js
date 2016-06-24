//jscs:disable
module.exports = function(grunt) {

  'use strict';

  require('jit-grunt')(grunt);

  /* CONFIGURATION =-=-=-=-=-=-=-=-=-=-=- */
  var THEME = 'activitycache';
  var SERVER_PORT = 3000;

  /* GRUNT INIT =-==-=-=-=-=-=-=-=-=-=-=- */
  grunt.initConfig({
    // package file
    pkg: grunt.file.readJSON('package.json'),
    paths: {
      src: './src',
      dist: './dist',
      assets: '<%= paths.dist %>/assets',
      styleguide: '<%= paths.dist %>/styleguide',
      tmp: './.tmp'
    },

    //generate svg sprites and fallback pngs
    // https://github.com/jkphl/grunt-svg-sprite
    svg_sprite: {
      all: {
        expand: true,
        cwd: 'src',
        src: 'sprites/**/*.svg',
        dest: 'src',
        options: {
          // Task-specific options go here.
          shape: {
            spacing: {
              padding: 2,
              box: 'padding'
            },
            id: {
              generator: function(name) {
                console.log(name);
                var separator = name.indexOf('/') ? '/' : '\\';
                var startIndex = name.lastIndexOf(separator) + 1;
                var endIndex = name.lastIndexOf('.');
                return name.substring(startIndex, endIndex);
              }
            },
            transform: ['svgo']
          },
          mode: {
            css: {
              render: {
                scss: {
                  dest: '../sass/_sprites.scss',
                  template: 'src/sass/sprite-template.html'
                }
              },
              dest: 'img',
              sprite: 'sprites.svg',
              bust: false,
              common: 'icn',
              prefix: '%s',
              dimensions: true
            }
          }
        }
      }
    },

    // https://github.com/sindresorhus/grunt-svgmin
    svgmin: {
      options: {
        plugins: [{
          removeViewBox: false
        }, {
          removeUselessStrokeAndFill: false
        }]
      },
      dist: {
        files: {
          'src/img/sprites.svg': 'src/img/sprites.svg'
        }
      }
    },

    // https://github.com/dbushell/grunt-svg2png
    svg2png: {
      all: {
        // specify files in array format with multiple src-dest mapping
        files: [
          // rasterize all SVG files in "img" and its subdirectories to "img/png"
          {
            cwd: '<%= paths.src %>/img/',
            src: ['**/*.svg'],
            dest: '<%= paths.asets %>/img/'
          }, {
            cwd: '<%= paths.src %>/img/',
            src: 'sprites.svg',
            dest: '<%= paths.asets %>/css/'
          }
        ]
      }
    },

    // generate pages from handlebar templates
    // http://assemble.io/
    assemble: {
      options: {
        data: ['<%= paths.src %>/data/*.{json,yml}'],
        flatten: true,
        helpers: '<%= paths.src %>/templates/helpers/**/*.js',
        layout: 'site.hbs',
        layoutdir: '<%= paths.src %>/templates/layouts',
        partials: '<%= paths.src %>/templates/partials/*.hbs',
        theme: THEME
      },
      main: {
        files: [{
          expand: true,
          cwd: '<%= paths.src %>/templates/pages',
          src: '*.hbs',
          dest: '<%= paths.dist %>'
        }]
      }
    },

    // clear a directory before build
    // https://github.com/gruntjs/grunt-contrib-clean
    clean: {
      all: ['<%= paths.dist %>', '<%= paths.tmp %>']
    },

    // create a local server
    // https://github.com/gruntjs/grunt-contrib-connect
    connect: {
      server: {
        options: {
          port: SERVER_PORT,
          base: ['/bower_components', 'dist'],
          hostname: '*',
          livereload: true,
          protocol: 'http'
        }
      }
    },

    // copy files
    // https://github.com/gruntjs/grunt-contrib-copy
    copy: {
      options: {
        processContentExclude: ['.DS_Store', '.gitignore', '.sass-cache',
          'node_modules', 'src/tests/**'
        ]
      },
      fonts: {
        files: [{
          expand: true,
          cwd: '<%= paths.src %>',
          dest: '<%= paths.assets %>',
          src: ['fonts/**'],
          filter: 'isFile'
        }]
      },
      svgs: {
        files: [{
          cwd: '<%= paths.src %>',
          dest: '<%= paths.assets %>',
          src: ['img/**/*.svg'],
          expand: true,
          filter: 'isFile'
        }]
      },
      scripts: {
        files: {
          '<%= paths.assets %>/js/_staticonly.js': '<%= paths.src %>/js/_staticonly.js'
        }
      },
      mocks: {
        files: [{
          cwd: '<%= paths.src %>/js/mock',
          dest: '<%= paths.assets %>/js/mock',
          src: '**/*',
          expand: true,
          filter: 'isFile'
        }]
      },
      sprites: {
        files: {
          '<%= paths.assets %>/css/sprites.svg': '<%= paths.src %>/img/sprites.svg'
        }
      }
    },

    // minify images
    // https://github.com/gruntjs/grunt-contrib-imagemin
    imagemin: {
      build: {
        options: {
          optimizationLevel: 3
        },
        files: [{
          expand: true,
          cwd: '<%= paths.src %>',
          src: ['img/**/*.{jpg,png}',
            'imgresponsive/**/*.{jpg,png}'
          ],
          dest: '<%= paths.assets %>'
        }]
      }
    },

    // https://github.com/gruntjs/grunt-contrib-jshint
    jshint: {
      options: {
        jshintrc: '.jshintrc'
      },
      dev: {
        src: ['<%= paths.src %>/js/**/*.js', '!' + '<%= paths.src %>/js/libs/**/*.js']
      },
      gruntfile: {
        src: ['Gruntfile.js']
      }
    },

    // https://github.com/gruntjs/grunt-contrib-sass
    sass: {
      dev: {
        options: {
          sourceMap: true
        },
        files: {
          '<%= paths.tmp %>/css/styles.css': '<%= paths.src %>/sass/styles.scss'
        }
      }
    },

    // convert pixels to rem units and add vendor prefixes
    // https://github.com/nDmitry/grunt-postcss
    postcss: {
      dev: {
        options: {
          map: false,
          processors: [
            require('pixrem')(),
            require('autoprefixer')({browsers: 'last 2 versions'}),
            require('cssnano')()
          ]
        },
        files: [{
          src: ['<%= paths.tmp %>/css/styles.css'],
          dest: '<%= paths.assets %>/css/styles.css'
        }]
      }
    },

    // different watch options trigger different tasks
    // https://github.com/gruntjs/grunt-contrib-watch
    watch: {
      options: {
        livereload: true
      },
      assemble: {
        files: ['./src/**/*.hbs'],
        tasks: ['newer:assemble']
      },
      assembleData: {
        files: ['./src/data/**/*.yml'],
        tasks: ['assemble']
      },
      gruntfile: {
        options: {
          reload: true
        },
        files: ['gruntfile.js'],
        tasks: ['build']
      },
      images: {
        files: ['src/img/**/*.{jpg, png, svg}'],
        tasks: ['images', 'styles']
      },
      sass: {
        files: ['src/sass/**/*.scss'],
        tasks: ['styles', 'kss']
      },
      scripts: {
        files: ['src/js/**/*.js'],
        tasks: ['scripts']
      },
      scriptsMocks: {
        files: ['src/js/mock/*.json'],
        tasks: ['scripts']
      },
      sprites: {
        files: ['src/sprites/*.svg'],
        tasks: ['build']
      }
    },
    browserify: {
      options: {
        exclude: ['moment'],
        browserifyOptions: {
          debug: true
        }
      },
      main: {
        files: {
          'dist/assets/js/setlist/main.js': ['src/js/main.js'],
        }
      }
    }
  });

  /* TASKS =-=-=-=-=-=-=-=-=-=-=-=-=-=-=- */

  // tasks for images, scripts and styles
  grunt.registerTask('images', ['svg2png', 'imagemin:build', 'copy:svgs']);
  grunt.registerTask('sprites', ['svg_sprite', 'svgmin']);
  grunt.registerTask('scripts', ['browserify:main', 'copy:scripts', 'copy:mocks']);
  grunt.registerTask('styles', ['sass', 'postcss:dev', 'copy:sprites']);
  grunt.registerTask('generic', ['images', 'svg_sprite', 'copy:fonts']);
  // tasks to run a complete build
  grunt.registerTask('build', ['clean', 'assemble', 'scripts', 'styles', 'generic']);
  grunt.registerTask('watchDev', ['build', 'connect:server', 'watch']);
  // main tasks
  grunt.registerTask('default', ['watchDev']);

};
/* jshint ignore:end */
