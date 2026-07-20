// LeetCode 1260 - Shift 2D Grid (https://leetcode.com/problems/shift-2d-grid/)

var shiftGrid = function (grid, k) {
    // flatten the 2D array i.e, 2D array -> 1D array
    const flat = []
    grid.forEach((element) => {
        flat.push(...element);
    });

    // now check the minimum number or rotation required
    // eg, 2D grid = [[1]] after k = 100 rotation -> grid = [[1]] -> no rotation 
    // therefore we need to updated the k values for minimum rotation
    k = k % flat.length;
    if (k === 0) {  // return if no rotation
        return grid;
    }
    
    // shift k element to front
    const shift = [];
    for (let i = 0; i < k; i++) {   // first shift the last K element to front 
        let last = flat.length - 1 - i;
        shift.unshift(flat[last]);      // upshift = add element at first place
    }
    shift.push(...flat.slice(0, flat.length - k));  // fill the remaining element -> since we already fill the last K element we just need to skip the last K element (flat.length - k)
    
    // convert the shifted 1D flatten array to shifted 2D grid  
    const ans = [];
    const col = grid[0].length; // number of columns in in grid

    for (let i = 0; i < grid.length; i++) { // adding each row into the 2D grid
        ans.push(shift.slice(i * col, i * col + col));
    }

    // return the shifted 2D grid
    return ans;
};

console.log(shiftGrid([[1], [2], [3], [4], [7], [6], [5]], 23));

/*
[
  [ 6 ], [ 5 ],
  [ 1 ], [ 2 ],
  [ 3 ], [ 4 ],
  [ 7 ]
]
*/
