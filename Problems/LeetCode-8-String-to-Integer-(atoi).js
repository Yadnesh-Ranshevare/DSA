// LeetCode 8 -String to Integer (atoi) (https://leetcode.com/problems/string-to-integer-atoi/description/)
/** 
Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
 
Example:
Input: s = "1337c0d3"
Output: 1337
 */

/*
ASCII values:
"+" = 43
"-" = 45
0 = 48
1 = 49
.
.
.
9 = 57
*/

const INT_MIN = -(2 ** 31);
const INT_MAX = 2 ** 31 - 1;

function RoundOffTo32Bit(x) {
    if (x > INT_MAX) {
        return INT_MAX;
    }

    if (x < INT_MIN) {
        return INT_MIN;
    }

    return x;
}

var myAtoi = function (s) {
    s = s.trim();   // remove the whitespace

    if (s === "") return 0; // return 0 if no number present 

    let ans = 0;
    const sign = s[0].charCodeAt(0) === 45 ? -1 : 1;    // ASCII value of "-" = 45

    s = sign === -1 ? s.slice(1)    // remove that "-" sign 
        : s[0].charCodeAt(0) === 43 ? s.slice(1) : s;   // remove "+" sign if exist ->  ASCII value of "+" = 43

    for (char of s) {
        const num = parseInt(char)    // convert the string to number i.e, "1" -> 1 and "a" -> NaN 
        if (Number.isNaN(num)) {    // check for non integer char
            break;  // stop the loop if char is non integer value
        }
        ans = ans * 10 + num;   // append the number at last
    }
    return RoundOffTo32Bit(ans * sign); // return the 32 bit round off value 
};

console.log(myAtoi("1337c0d3"))
// console.log(myAtoi("-91283472332"));
