// LeetCode 7 - Reverse Integer (https://leetcode.com/problems/reverse-integer/description/)

/*
Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-2^(31), 2^(31) - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

Example 1:
Input: x = 123
Output: 321

Example 2:
Input: x = -123
Output: -321

Example 3:
Input: x = 120
Output: 21
*/

/**
 * @param {number} x
 * @return {number}
 */

const INT_MIN = -(2 ** 31);
const INT_MAX = 2 ** 31 - 1;

function is32BitInteger(x) {
    return x >= INT_MIN && x<= INT_MAX;
}

var reverse = function (x) {
    const sign = x < 0 ? -1 : 1;
    x = Math.abs(x);

    let reversed = 0;
    while (x != 0) {
        let lastDigit = Number(x % 10);     // accessing the last element of the input integer

        reversed = reversed * 10 + lastDigit; // adding the last digit at the end of reversed
        // lastDigit = 1 ; reverse = 5 -> reverse * 10 = 50 -> 50+ lastDigit = 51  

        x = Math.trunc(x / 10); // remove the last digit from input
        // Math.trunc(12.3) = 12
    }

    reversed *= sign       // assign the respective sign to reverse integer

    if (!is32BitInteger(reversed)) return 0;    // check whether reverse is 32bit integer or not

    return reversed;
};

console.log(reverse(1534236469));
