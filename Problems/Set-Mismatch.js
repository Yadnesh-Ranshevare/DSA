/*
You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.

You are given an integer array nums representing the data status of this set after the error.

Find the number that occurs twice and the number that is missing and return them in the form of an array.

Example 1:

Input: nums = [1,2,2,4]
Output: [2,3]
Example 2:

Input: nums = [1,1]
Output: [1,2]

What exactly do you have to return?

An array of 2 elements:
[duplicate_number, missing_number]
*/

var findErrorNums = function (nums) {
    const visited = [];   // caries the record of visited element
    let missing = null;
    let duplicate = null;

    for (let i = 0; i < nums.length; i++) { // start form 0 to n-1 (nums.length)
        const currValue = i + 1     // current value that should be present in set; why +1 -> according to question set has value from 1 to n but in this loop we have 0 to n-1
        if (!nums.includes(currValue)) {    // check whether currValue present in the nums array or not
            missing = currValue;    // if not present -> we found the missing 
        }

        if (duplicate) {    // check whether we found the duplicate value or not
            continue;   // skip the next comparisons if duplicate was already found
        }

        if (!visited.includes(nums[i])) {     // check whether or not we have encounter the nums[i] 
            visited.push(nums[i]);  // if not visited add the record into visited array
        } else {
            duplicate = nums[i];    // if visited -> we found the duplicates
        }
    }

    return [duplicate, missing];
};

console.log(findErrorNums([1, 2, 2, 4, 5, 6]));
