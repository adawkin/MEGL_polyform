use std::collections::HashSet;

#[derive(Debug, PartialEq, Eq, Hash)]
struct PolyominoPiece(u64, u64, u64);

type Polyomino = HashSet<PolyominoPiece>;

impl PolyominoPiece {
    fn new() -> PolyominoPiece {
        return PolyominoPiece(0, 0, 0);
    }
}

/// Aligns a polyomino to the bottom left corner i.e.
/// For each dimension, there exists at least one polyomino piece whose co-ordinate is a zero in
/// that dimension
fn align_polyomino() {
}

/// Function checks if the given polyominos (possibly former neighbors) still have a connection
/// between them
fn are_neighbors_connected(polyomino: Polyomino, neighbors: Vec<PolyominoPiece>) {
    let mut known_connected = Polyomino::new();
    let mut progress_made = true;
    loop {
        progress_made = false;

        for i in polyomino {
        }

        // Check if we've found all the neighbors
        let mut has_all_pieces = true;
        for piece in neighbors {
            if !known_connected.contains(&piece) {
                has_all_pieces = false;
            }
        }
        if has_all_pieces {
            break;
        }

        // check if we were able to add any new pieces through the last pass
        // if we couldn't make progress, then we're done (unsuccesfully, since we already checked
        // if all the pieces we're looking for are in)
        if !progress_made {
            break;
        }
        
    }
}

/// Creates a flat, horizontal polyomino
fn create_horizontal_polyomino(n: u64) -> Polyomino {
    let mut polyomino = HashSet::<PolyominoPiece>::new();
    for i in 0..n {
        let mut piece = PolyominoPiece::new();
        piece.0 = i;
        polyomino.insert(piece);
    }
    polyomino
}

fn main() {
    let polyomino = create_horizontal_polyomino(5);
    println!("{:?}", polyomino);
}
