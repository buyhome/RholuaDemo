require 'rho/rhocontroller'
require 'helpers/application_helper'
require 'helpers/browser_helper'
require 'rholua'

class RholuaTestController < Rho::RhoController
  include BrowserHelper
  include ApplicationHelper
  
  def index
    render :back => '/app'
  end

  def run_test
    sum = Rholua.calc_summ(3,7) 
    Alert.show_popup Rholua::RholuaStringHelper::process_string('test')+sum.to_s
    render :action => :index, :back => '/app'
  end
  
end
