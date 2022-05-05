// use remove_swap and push to back for faster removals and insertions (for array representation of
// polyomino)
use std::collections::{HashSet, VecDeque};

type Polyomino = HashSet<(i32, i32, i32)>;

fn generate_polyomino(n: i32) -> Polyomino {
    // generate a stick polyomino. Linear time
    let mut mino = Polyomino::new();
    for i in 0..n {
        mino.insert((0,0,i));
    }
    mino
}

fn get_perimeter(mino: &Polyomino) -> HashSet<(i32, i32, i32)> {
    // iterative get perimeter algorithm
    // later on, I can optimize by tracing the outer perimeter. The problem is contained holes
    let mut perimeter = HashSet::<(i32, i32, i32)>::new();
    for piece in mino {
        //if any of its neighbors are an empty space, add to perimeter
        if !mino.contains(&(piece.0+1, piece.1, piece.2)) {
            perimeter.insert((piece.0+1, piece.1, piece.2));
        }
        if !mino.contains(&(piece.0-1, piece.1, piece.2)) {
            perimeter.insert((piece.0-1, piece.1, piece.2));
        }

        if !mino.contains(&(piece.0, piece.1+1, piece.2)) {
            perimeter.insert((piece.0, piece.1+1, piece.2));
        }
        if !mino.contains(&(piece.0, piece.1-1, piece.2)) {
            perimeter.insert((piece.0, piece.1-1, piece.2));
        }

        if !mino.contains(&(piece.0, piece.1, piece.2+1)) {
            perimeter.insert((piece.0, piece.1, piece.2+1));
        }
        if !mino.contains(&(piece.0, piece.1, piece.2-1)) {
            perimeter.insert((piece.0, piece.1, piece.2-1));
        }
    }

    return perimeter;
}

fn update_perimeter(mino: &Polyomino, perimeter: &mut HashSet<(i32, i32, i32)>, piece: (i32, i32, i32)) {
    // takes a cached perimeter and updates it according to the shuffling. Constant time
    
    // remove perimeter pieces adjacent to the removed piece
    // add removed piece to the perimeter
    // check on all sides of the removed piece to see if we should re-add perimeter piece

    let visited = HashSet::<(i32, i32, i32)>::new();
}

fn cut_validity_algorithm(mino: Polyomino, removed_piece: (i32, i32, i32)) {
    // This algorithm uses the removed piece to see if there's a path it can line that separates
    // the two polyforms. when I came up with this algorithm, I figured it would run a lot faster
    // because the distance from a piece to the perimeter is relatively short and doesn't require
    // full DFS of the polyomino
    //
    // if we can find a path to the perimeter 
}

// abandoned for now, in favor of cut validity. should be revived so we can benchmark the two
// algorithms for different cases and decide if/when to switch algorithms based on some kind of
// predictor. I realized this will execute much quicker in cases where there is an actual valid
// connection, but the same amount of time for DFS to conclude that there is no valid conncetion.
// Replaced with the cut algorithm. After a while, the optimized DFS may run quicker than cut
// algorithm
/*
fn optimized_dfs_check_validity(mino: &Polyomino, removed_piece: (i32, i32, i32)) -> bool {

    // heuristic-optimized check validity DFS (requires set implementation)
    // should terminate after very few steps in most cases, but O(n) worst-case
    // basically DFS in the direction of the neighbors that haven't been found yet, starting with
    // the neighbor

    // find all of the removed piece's neighbors

    // pick one of the neighbors and seek out one of the other neighbors
    // then seek out the next nearest neighbor
    // so on til all neighbors are found


    // this set contains all the neighbors. when a neighbor is eventually found it 
    let neighbors = Vec::<(i32, i32, i32)>::new();

    for piece in mino {
        //if any of its neighbors are an empty space, add to perimeter
        if mino.contains(&(piece.0+1, piece.1, piece.2)) {
            neighbors.push((piece.0+1, piece.1, piece.2));
        }
        if mino.contains(&(piece.0-1, piece.1, piece.2)) {
            neighbors.push((piece.0-1, piece.1, piece.2));
        }

        if mino.contains(&(piece.0, piece.1+1, piece.2)) {
            neighbors.push((piece.0, piece.1+1, piece.2));
        }
        if mino.contains(&(piece.0, piece.1-1, piece.2)) {
            neighbors.push((piece.0, piece.1-1, piece.2));
        }

        if mino.contains(&(piece.0, piece.1, piece.2+1)) {
            neighbors.push((piece.0, piece.1, piece.2+1));
        }
        if mino.contains(&(piece.0, piece.1, piece.2-1)) {
            neighbors.push((piece.0, piece.1, piece.2-1));
        }
    }

    // keep track of nodes we still have to visit
    let stack = VecDeque::<(i32, i32, i32)>::new();

    // must have at least one neighbor if the polyomino was ever valid
    stack.push_back(neighbors.swap_remove(0));
    while(!stack.is_empty()) {
        let piece = stack.pop_back();
        // push the piece most in the direction of our nieghbor first
        // if we find a neighbor, remove it from the neighbors list and set target to the next
        // neighbor
    }

    // better than a random DFS, depending on how far the connection is
    // non-recursive user stack implementation to avoid hitting recursion limits
    let visited = HashSet::<(i32, i32, i32)>::new();
    stack.push_back()

}*/

fn main() {
    println!("Hello, world!");
}
