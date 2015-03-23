# roadtracking
We will provide you a powerful road tracking with the benefit of your privacy from your gained data... You will be able to see and share your score and your total statistics with your friends.

## Prerequisites
* Java - Download and Install [Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html).
* Gradle - Download and Install [Gradle](https://www.gradle.org/downloads/).
* Node.js - Download and Install [Node.js](http://www.nodejs.org/download/).
* Ruby - Download and Install [Ruby](https://www.ruby-lang.org/en/downloads/).

### Tools Prerequisites
* NPM - Node.js package manager, should be installed when you install node.js.
* Grunt - Install the [Grunt Task Runner](http://gruntjs.com/) to automate your development process.

```bash
$ npm install -g grunt-cli
```

## Getting Started
The easiest way to get started is to clone the repository:

```bash
### Get the latest snapshot
$ git clone https://github.com/mediahackday/roadtracking.git roadtracking
$ cd roadtracking

### Install NPM dependencies
$ npm install
```

### Test Application
Before your run the build it is recommanded to run all the tests:

```bash
$ grunt test
```

### Build Application
After the install process is over, you'll be able to build the application using Grunt:

```bash
$ grunt build
```

## Troubleshooting
During install some of you may encounter some issues, most of this issues can be solved by one of the following tips.

#### Update NPM or Grunt
Sometimes you may find there is a weird error during install like npm's *Error: ENOENT*, usually updating those tools to the latest version solves the issue.

Updating NPM:
```bash
$ npm update -g npm
```

Updating Grunt:
```bash
$ npm update -g grunt-cli
```

#### Cleaning NPM
NPM has a caching system for holding packages that you already installed. We found that often cleaning the cache solves some troubles this system creates.

NPM Clean Cache:
```bash
$ npm cache clean
```

## Motivation
This project was build with a team of six young innovators from all over Europe. We just met at the [Media Hack Day](http://www.mediahackday.com) in Berlin and formed the team. We scratched and developed the PointUp Webapp in between 24h. For Further Information just checkout our site on [Hackerleague](https://www.hackerleague.org/hackathons/media-hack-day-connected-car/hacks/roadtracking).

## API Reference
* [Web Storage](http://www.w3.org/TR/webstorage/)
* [Geolocation API](http://www.w3.org/TR/geolocation-API/)
* [RESTful API](http://en.wikipedia.org/wiki/Representational_state_transfer)

## Contributors
[Aileen](https://www.xing.com/profile/Aileen_Tschoepe), [Barbara](https://twitter.com/Barbara_Tsingas), [Wilko](https://www.xing.com/profile/Wilko_Malchau), [Alex](http://de.linkedin.com/in/alittorin), Alex and [Aurelius](https://twitter.com/webtobesocial)

## Beta
[PointUp](http://roadtracking-pro.appspot.com)

## Credits
* [Bosch mySPIN](http://www.bosch-softtec.com/myspin.html) for supporting us with their API.
* [Plug&Play Accelerator](http://www.axelspringerplugandplay.com) for hosting this hackathon. 
* [All](https://www.hackerleague.org/hackathons/media-hack-day-connected-car/participations) participants for the awesome time with you.

## License
Code released under the [MIT](https://github.com/mediahackday/roadtracking/blob/master/LICENSE) license.