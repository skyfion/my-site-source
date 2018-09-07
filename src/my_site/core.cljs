(ns my-site.core
  (:require [reagent.core :as r]
            [clojure.browser.repl :as repl]
            [quil.core :as q :include-macros true]
            [quil.middleware :as m]
            [taoensso.timbre :as log]))

(enable-console-print!)


(defn current-page []
  #_[:canvas#canvas]
  [:div.jumbotron.mt-3
   [:div.card-body
    [:h5 "Title"]
    [:p "test hello world"]]])

#_(defn white-noise-init []
  (let [canvas  (.getElementById js/document "canvas")
        ctx  (.getContext canvas "2d")
        w 700 h 500]
    (set! (.-width canvas) w)
    (set! (.-height canvas) h)
    (set! (.-fillStyle ctx) "white")
    (.fillRect ctx 0 0 w h)
    (.fill ctx)
    (js/setInterval #(let [img-data (.getImageData ctx 0 0 w h)
                           pix (.-data img-data)]
                       (doseq [i (range 0 (dec (.-length pix)) 4)]
                         (let [color (+ 50 (* 255 (js/Math.random)))]
                           (doseq [idx [(+ 0 i) (+ 1 i) (+ 2 i)]]
                             (aset pix idx color))))
                       (.putImageData ctx img-data 0 0)
                       ) 30)

    )
  )

;
;(defn setup []
;  (q/frame-rate 5)
;  (q/background 255)
;  {:pixel (q/pixels)}
;  )

;(defn get-lamp-index []
;  (if (and (< 0 (q/mouse-x) (q/width))
;           (< 0 (q/mouse-y) (q/height)))
;    [(quot (q/mouse-x) step)
;     (quot (q/mouse-y) step)]
;    nil))

;(defn update-state [state]
;  #_(q/pixels (map #(q/random 200 255) (q/pixels)))
;  (let [pixels (:pixels state)]
;    (doseq [i (range 0 (- (count pixels) 5) 4)]
;      (let [color (q/color (q/random 100 255))]
;        (assoc state :pixels
;                     i color
;                     (+ 1 i) color
;                     (+ 2 i) color)
;        )
;      )
;    )
;
;  state
;  )
;
;(defn draw-state [state]
;  (q/update-pixels )
;  (q/ellipse 56 46 55 55)
;  )
;
;(q/defsketch dry-paint
;             :host "canvas"
;             :size [600 600]
;             :features [:present]
;             :setup setup
;             :update update-state
;             :draw draw-state
;             :middleware [m/fun-mode])

(defn mountit []
  (r/render [current-page] (.getElementById js/document "app")))

(mountit)

