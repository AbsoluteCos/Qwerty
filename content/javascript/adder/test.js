const theCode = require(process.argv[1])
if (theCode.calculate([2, 'times', 5]) !== 10) {
    console.error("[2, 'times', 5] didn't work")
}