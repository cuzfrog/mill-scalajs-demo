package demo.action

import diode.Action

sealed trait NavigationAction extends Action with Product with Serializable

case object LoginAction extends NavigationAction