// LeetCode 6 - Zigzag Conversion (https://leetcode.com/problems/zigzag-conversion/)

/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
*/

/**
 * @param {string} s
 * @param {number} numRows
 * @return {string}
 */
var convert = function(s, numRows) {
    // edge case where input string forms a string line in zig zak pattern 
    if (numRows === 1 || s.length <= numRows) return s;

    let row = 0;    // current row
    let up = false  // true for going up and false for going down in zig-zag pattern
    const ans = []; //  store the output string
    
    for(char of s){
        ans[row] = ans[row]? ans[row] + char : char;    // store the character in the current row
        if(row === numRows - 1){    // if current row is last row then go up
            up = true
        }else if(row === 0){    // if current row is first row then go down
            up = false
        }
    }

    return ans.join("")

};

console.log(convert("abcdefghi", 4))    // abcdefghi
// console.log(convert("PAYPALISHIRING", 4))       // output: "PAYPALISHIRING"