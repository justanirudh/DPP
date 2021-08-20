from glob import glob
import os
pre = "Scala_"
[os.rename(f, "{}{}".format(pre, f)) for f in glob("*.scala")]
