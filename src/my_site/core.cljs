(ns my-site.core
  (:require [reagent.core :as r]
            [clojure.browser.repl :as repl]
            [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

(enable-console-print!)

(defn current-page []
  [:canvas#canvas])

(defn pulse [low high rate millis]
  (let [diff (- high low)
        half (/ diff 2)
        mid (+ low half)
        s (/ millis 1000.0)
        x (q/sin (* s (/ 1.0 rate)))]
    (+ mid (* x half))))

(def letter-Q [[3 2] [2 3] [2 4] [2 5] [2 6] [3 7]
               [4 8] [5 8]
               [5 6] [6 7] [7 8]
               [7 6] [7 5] [7 4] [7 3] [6 2]
               [5 1] [4 1]])

(def step 20)

(defn create-color [millis]
  [(pulse 0 255 3.0 millis)
   (pulse 0 255 5.0 millis)
   (pulse 0 255 7.0 millis)])

(defn setup []
  (q/frame-rate 100)
  (q/background 255)
  (zipmap letter-Q
          (map create-color (iterate #(+ % 200) 10000))
          ))

(defn get-lamp-index []
  (if (and (< 0 (q/mouse-x) (q/width))
           (< 0 (q/mouse-y) (q/height)))
    [(quot (q/mouse-x) step)
     (quot (q/mouse-y) step)]
    nil))

(defn update-state [state]
  (if-let [index (get-lamp-index)]
    (assoc state index (create-color (q/millis)))
    state))

(defn draw-state [state]
  (q/no-stroke)
  (q/background 255)
  (q/no-stroke)
  (doseq [[ind col] state]
    (let [x (* step (first ind))
          y (* step (second ind))
          col-mod (-> (+ x y (q/millis))
                      (* 0.01)
                      (q/sin)
                      (* 5))]
      (apply q/fill (map + col (repeat 3 col-mod)))
      (q/rect x y step step))))

(q/defsketch dry-paint
             :host "canvas"
             :size [600 600]
             :features [:present]
             :setup setup
             :update update-state
             :draw draw-state
             :middleware [m/fun-mode])

(defn mountit []
  (r/render [current-page] (.getElementById js/document "app")))

#_(mountit)

