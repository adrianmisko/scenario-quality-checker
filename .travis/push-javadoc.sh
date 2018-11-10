#!/bin/sh

if [    "$TRAVIS_REPO_SLUG" = "adrianmisko/scenario-quality-checker" -a \
        "$TRAVIS_PULL_REQUEST" = "false" -a \
        "$TRAVIS_BRANCH" = "master" ]
then

  echo "Publishing javadoc..."

  cp -R target/site/apidocs $HOME/apidocs

  cd $HOME
  git rm -rf ./scenario-quality-checker/apidocs
  mkdir -p ./scenario-quality-checker/apidocs
  cp -Rf $HOME/apidocs ./scenario-quality-checker
  git add -f .
  git commit -m "Latest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed"
  git push -fq origin master > /dev/null

  echo "Published Javadoc."

fi
