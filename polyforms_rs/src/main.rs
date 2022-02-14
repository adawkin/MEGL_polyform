use rand::prelude::*;
use poloto::prelude::*;

fn main() {
    let khois_matrix = &[
        [5f32/9f32  , 0f32      , 1f32/9f32 , 1f32/9f32 , 1f32/9f32 , 1f32/9f32 ],
        [0f32       , 5f32/9f32 , 1f32/9f32 , 1f32/9f32 , 1f32/9f32 , 1f32/9f32 ],
        [1f32/9f32  , 1f32/9f32 , 7f32/18f32, 1f32/9f32 , 1f32/9f32 , 1f32/6f32 ],
        [1f32/9f32  , 1f32/9f32 , 1f32/9f32 , 7f32/18f32, 1f32/6f32 , 1f32/9f32 ],
        [1f32/9f32  , 1f32/9f32 , 1f32/9f32 , 1f32/6f32 , 7f32/18f32, 1f32/9f32 ],
        [1f32/9f32  , 1f32/9f32 , 1f32/6f32 , 1f32/9f32 , 1f32/9f32 , 7f32/18f32],
    ];
    

    for T in [1, 10, 100, 1_000, 10_000] {
        let mut histogram = [0i128; 7];
        for _ in 1..100_000 {

            // 0 is a
            let mut cur_polyomino = 0;

            for _ in 0..T {
                // get a random number between 0 and 1
                let random: f32 = thread_rng().gen();

                // interpret the random number, weighted by column elements
                let mut accum: f32 = 0f32;
                for i in 0..6 {
                    accum += khois_matrix[cur_polyomino][i];
                    if random < accum {
                        cur_polyomino = i;
                        break;
                    }
                }
            }

            // record the end polyomino
            histogram[cur_polyomino as usize] += 1;
            
        }

        // sum for later printing
        let mut sum = 0;
        for i in histogram {
            sum += i;
        }
        println!("for T={}", T);
        for (i, col) in histogram.iter().enumerate() {
            println!("{} {}, {}",(('a' as u8)+(i as u8)) as char, *col as f32/sum as f32, col);
        } 
        println!();

        // I still don't really know how to use this plotting library
        let data = poloto::data()
            .histogram("", (0..7).zip(histogram.into_iter()))
            .build();

        // mostly just following their documentation here
        let x = data.boundx().default_ticks();
        let y = data.boundy().default_ticks();
        let title = format!("Probabilities for T={}",T);

        // generate plots
        let mut plotter = data.plot_with(
            &title,
            "Polyomino",
            "Times found",
            x,
            y,
        );

        // render and write to files
        let _ = std::fs::write(&format!("hist_{}.svg", T), &format!("{}", poloto::disp(|w| plotter.simple_theme(w))));
    }


    //let current_iteration = [1f32, 0f32, 0f32, 0f32, 0f32, 0f32]; //using a row vector here because A * B = transpose(B) * A, and its easier to write a column vector

}


