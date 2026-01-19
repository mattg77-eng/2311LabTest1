# .bashrc

# Load central setup.

if [ -f /eecs/local/share/bashrc.common ]; then
  . /eecs/local/share/bashrc.common
fi

# The default umask creates files which are not readable by group or others.
# Uncomment the following to make created files readable by group or others.
#umask 022
