const theCode = require('./template.js')
const count = theCode.countCharactersInFile()
if (count !== 64) {
    console.error("There were 64 characters in that file. Double-check your code.")
}