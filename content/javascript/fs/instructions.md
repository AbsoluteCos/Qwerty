# Accessing the File System

In Node.js, there's a module called `fs` that lets us read and write to files on the hard drive.

## A quick note on modules
Some objects and functions aren't available automatically. They're part of modules, which you have to explicitly say
you're using. You can do this with the `require` function, passing in the name of the module like this:

```
const fs = require('fs')
```

A lot of essential modules, like `fs`, are already installed along with Node.js. There are thousands of others available via the [Node Package Manager](https://npmjs.org).

## Reading files
You can read a file like this:
```
const content = fs.readFileSync('./some-file.txt', {encoding: "utf-8"})
```

Now `content` will be a string containing the content of the file. For text files, make sure you specify the encoding. Otherwise, you'll just get the raw bytes back.

Note: the `sync` part of the function name means that your program will pause until it's done reading the file. Much of the time, you'll want to do it asynchronously, but we can safely ignore that for now.

## Practice reading files
<download src='template.js;./haiku.txt'>

Your turn! Download these files and try to make a program that prints out the number of characters in `haiku.txt`.
