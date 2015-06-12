for file in *.java; do
    mv "$file" "`basename $file .java`.groovy"
done