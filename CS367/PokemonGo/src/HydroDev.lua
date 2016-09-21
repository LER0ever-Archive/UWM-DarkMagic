sh = require("sh")
HydroJava = require("HydroJava")
Hydro = require("Hydro")

function config()
    print(HydroJava.detectJava())
    HydroJava.setShowAll(true)
end

function HydroDev.default()
    Env.Path()
    config()
    HydroJava.build()
end

function target.clean()
    clean()
end

function clean()
    HydroDev.sh("rm -f *.class")
end


