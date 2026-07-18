// LeetCode 1979 - Find Greatest Common Divisor of Array (https://leetcode.com/problems/find-greatest-common-divisor-of-array)
/*
Given an integer array nums, return the greatest common divisor of the smallest number and largest number in nums.

The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.
*/

var findGCD = function (nums) {
    // since we want to find the small and bigger value in array we start with opposite extreme values i.e, small = infinity & big = 0
    let small = Infinity; // small being infinity -> nums[i] will always be small -> small = nums[i]
    let big = 0; // and 0 being big -> nums[i] will always be big -> big = nums[i]

    nums.forEach((num) => {
        small = Math.min(small, num);   // checking for smaller value
        big = Math.max(big, num);       // checking for bigger value
    });

    for (let i = small; i > 0; i--) {   // finding common devisor
        if (small % i === 0 && big % i === 0) {
            return i;
        }
    }
};

console.log(findGCD([2, 5, 6, 9, 10]));
