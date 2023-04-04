#!/bin/sh

PACK_NAME="SmartCalc"
PACK_VERSION="3.0"
TMP_DIR="/tmp"
DIRECTORY_SOURCE=$PWD
KILOBIT=1024

mkdir -p $TMP_DIR/debian/lib
mkdir -p $TMP_DIR/debian/DEBIAN
mkdir -p $TMP_DIR/debian/usr/app
mkdir -p $TMP_DIR/debian/usr/share/$PACK_NAME
mkdir -p $TMP_DIR/debian/usr/share/applications
mkdir -p $TMP_DIR/debian/usr/share/doc/$PACK_NAME
mkdir -p $TMP_DIR/debian/usr/share/common-licenses/$PACK_NAME

echo "Package: $PACK_NAME" > $TMP_DIR/debian/DEBIAN/control
echo "Version: $PACK_VERSION" >> $TMP_DIR/debian/DEBIAN/control
cat control >> $TMP_DIR/debian/DEBIAN/control

cp *.desktop $TMP_DIR/debian/usr/share/applications/
cp copyright $TMP_DIR/debian/usr/share/common-licenses/$PACK_NAME/
cp ../*.jar $TMP_DIR/debian/usr/share/$PACK_NAME/
cp $PACK_NAME $TMP_DIR/debian/usr/app/

echo "$PACK_NAME: $PACK_VERSION, trusty; urgency=low" > changelog
echo "  * Rebuild" >> changelog
echo " -- Serge Helfrich <helfrich@xs4all.nl>  `date -R`" >> changelog
gzip -9c changelog > $TMP_DIR/debian/usr/share/doc/$PACK_NAME/changelog.gz

cp *.png $TMP_DIR/debian/usr/share/$PACK_NAME/
chmod 0664 $TMP_DIR/debian/usr/share/$PACK_NAME/*png

PACKAGE_SIZE=`du -bs $TMP_DIR/debian | cut -f 1`
PACKAGE_SIZE=$((PACKAGE_SIZE/KILOBIT))
echo "Install Size: $PACKAGE_SIZE" >> $TMP_DIR/debian/DEBIAN/control

chown -R root $TMP_DIR/debian/
chgrp -R root $TMP_DIR/debian/

cd $TMP_DIR/
dpkg --build debian
mv debian.deb $DIRECTORY_SOURCE/$PACK_NAME-$PACK_VERSION.deb
rm -r $TMP_DIR/debian