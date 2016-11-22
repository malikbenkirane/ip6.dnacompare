(TeX-add-style-hook
 "rapport"
 (lambda ()
   (TeX-add-to-alist 'LaTeX-provided-class-options
                     '(("article" "a4paper" "11pt")))
   (TeX-add-to-alist 'LaTeX-provided-package-options
                     '(("babel" "french") ("geometry" "margin=1in") ("hyperref" "pdftex" "colorlinks") ("xcolor" "dvipsnames")))
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "path")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "url")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "nolinkurl")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "hyperbaseurl")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "hyperimage")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "hyperref")
   (add-to-list 'LaTeX-verbatim-macros-with-delims-local "path")
   (TeX-run-style-hooks
    "latex2e"
    "partie-1"
    "partie-2"
    "partie-3"
    "mise-en-oeuvre"
    "article"
    "art11"
    "babel"
    "geometry"
    "hyperref"
    "amsmath"
    "color"
    "xcolor"
    "tkz-graph"
    "pgfplots")
   (TeX-add-symbols
    '("csection" 1)))
 :latex)

