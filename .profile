# .profile 

# Load central setup.
if [ -f /eecs/local/share/profile.common ]; then
  . /eecs/local/share/profile.common
fi

# Default path.
export PATH="$HOME/bin:/eecs/local/bin:/eecs/fac/bin:$SYSPATH:."
export MANPATH="$HOME/man:/eecs/local/man:/eecs/local/share/man:/eecs/fac/man:$SYSMANPATH"

if [ $(basename $SHELL) = "bash" ]  && [ -r ~/.bashrc ]; then
  . ~/.bashrc
fi
