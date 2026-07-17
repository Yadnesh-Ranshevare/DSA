// LeetCode 13 - Roman to Integer (https://leetcode.com/problems/roman-to-integer/description/)

/*
In roman if small symbol come before bigger one (like in "IV" -> "I" is small and "V" is bigger) then we just need to subtract that small symbol, whereas if next symbol is smaller one (like "VI" -> "V" is bigger and "I" is smaller) then we just need to add those both  

so to solve this problem we just compare two adjacent symbols 
- if next symbol is bigger one then subtract the current symbol from sum
- if next symbol is smaller one then add the current symbol into sum
*/


const symbol = {    // for symbol to int relation  
    I: 1,
    V: 5,
    X: 10,
    L: 50,
    C: 100,
    D: 500,
    M: 1000,
};

var romanToInt = function (s) {
    let ans = 0;    // final sum variable
    for (let i = 0; i < s.length; i++) {    // traverse over the input string 
        if(symbol[s[i]] < symbol[s[i+1]]){  // check if the next symbol is bigger one or not
            ans -= symbol[s[i]]             // if next one is bigger then subtract the current symbol
        }else{                              // is next symbol is smaller one
            ans += symbol[s[i]]             // if next one is smaller then just add the current symbol
        }
    }
    return ans; // return final sum
};

console.log(romanToInt("IX"));
