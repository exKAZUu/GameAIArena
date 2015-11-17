GameAIArena [![Build Status](https://api.travis-ci.org/exKAZUu/GameAIArena.png?branch=master)](https://travis-ci.org/exKAZUu/GameAIArena)
========================

## Developer

Kazunori SAKAMOTO (exkazuu@gmail.com, exkazuu@nii.ac.jp)

## Installation

```
    <dependency>
      <groupId>jp.ac.waseda.cs.washi</groupId>
      <artifactId>GameAIArena</artifactId>
      <version>1.7.2</version>
    </dependency>
```

or


```
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
	...
	<dependency>
	    <groupId>com.github.exKAZUu</groupId>
	    <artifactId>GameAIArena</artifactId>
	    <version>v1.7.4</version>
	</dependency>
```



## Javadoc

``` GameAIArena/doc ```

## License

Apache License, Version 2.0
http://www.apache.org/licenses/LICENSE-2.0

## How to Setup Development Environment

### Clone GameAIArena including Sample Game Project

1. ```git clone https://github.com/exKAZUu/GameAIArena.git```
1. Please copy the ```GameAIArena/SampleGame``` direcotry elsewhere if you don't want to modify my repo

### Prepare Eclipse Environment with Maven

1. Install Eclipse
  * Eclipse IDE for Java Developers (*not Standard*) Kepler (4.3)  
http://www.eclipse.org/downloads/

### Import Sample Project into Your Eclipse Workspace

1. Run Eclipse
1. Import > Existing Maven Projects
1. Enter the path of the ```GameAIArena/SampleGame``` direcotry in "root directory"
1. Select Projects
1. Finish
1. Right click the imported project > Maven > Update Project Configuration > OK
