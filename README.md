MyBatis Generator (MBG) (version 1.3.7)
=======================

[![Build Status](https://travis-ci.org/mybatis/generator.svg?branch=master)](https://travis-ci.org/mybatis/generator)
[![Coverage Status](https://coveralls.io/repos/mybatis/generator/badge.svg?branch=master&service=github)](https://coveralls.io/github/mybatis/generator?branch=master)
[![Maven central](https://maven-badges.herokuapp.com/maven-central/org.mybatis.generator/mybatis-generator/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.mybatis.generator/mybatis-generator)
[![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/https/oss.sonatype.org/org.mybatis.generator/mybatis-generator.svg)](https://oss.sonatype.org/content/repositories/snapshots/org/mybatis/generator/mybatis-generator)
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

![mybatis-generator](http://mybatis.github.io/images/mybatis-logo.png)

Code generator for MyBatis and iBATIS.

the sourcecode of the project from `https://github.com/mybatis/generator`


## Extend

### new Feature

#### batchInsertWithoutId

> invoke this method to batch insert List<T>(will not insert T.id)
  
 1. generate related xml
 2. generate related Mapper
 3. default generate the method
    
#### batchInsertWithId    

> invoke this method to batch insert List<T> (will insert T.id)
 
  1. generate related xml
  2. generate related Mapper
  3. default generate the method

