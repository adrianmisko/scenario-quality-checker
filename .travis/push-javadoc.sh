#!/bin/sh

if [    "$TRAVIS_REPO_SLUG" = "adrianmisko/scenario-quality-checker" -a \
        "$TRAVIS_PULL_REQUEST" = "false" -a \
        "$TRAVIS_BRANCH" = "master" ]
then

  echo "Publishing javadoc..."

  cp -R target/site/docs $HOME/docs
  
  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  
  git clone --quiet --branch=master https://${GH_TOKEN}@github.com/adrianmisko/scenario-quality-checker gh-pages > /dev/null 
  cd gh-pages
  git rm -rf ./scenario-quality-checker/docs
  mkdir -p ./scenario-quality-checker/docs
  cp -Rf $HOME/docs ./scenario-quality-checker
  git add -f .
  git commit -m "Latest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed [skip ci]"
  git push -fq origin master > /dev/null

  echo "Published Javadoc."

fi
