class PitchesController < ApplicationController
  before_action :set_pitch, only: [:show, :edit, :update, :destroy]

  # GET /pitches
  # GET /pitches.json
  def index
    @pitches = Pitch.select("pitches.*, COUNT(votes.id) AS tally").joins(:votes).group("pitches.id")
    @pitches = @pitches.order("tally DESC")
  end

  # GET /pitches/1
  # GET /pitches/1.json
  def show
  end

  # GET /pitches/new
  def new
    @pitch = Pitch.new
  end

  # GET /pitches/1/edit
  def edit
  end

  # POST /pitches
  # POST /pitches.json
  def create
    @pitch = Pitch.new(pitch_params)

    respond_to do |format|
      if @pitch.save
        format.html { redirect_to @pitch, notice: 'Pitch was successfully created.' }
        format.json { render :show, status: :created, location: @pitch }
      else
        format.html { render :new }
        format.json { render json: @pitch.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /pitches/1
  # PATCH/PUT /pitches/1.json
  def update
    respond_to do |format|
      if @pitch.update(pitch_params)
        format.html { redirect_to @pitch, notice: 'Pitch was successfully updated.' }
        format.json { render :show, status: :ok, location: @pitch }
      else
        format.html { render :edit }
        format.json { render json: @pitch.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /pitches/1
  # DELETE /pitches/1.json
  def destroy
    @pitch.destroy
    respond_to do |format|
      format.html { redirect_to pitches_url, notice: 'Pitch was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_pitch
      @pitch = Pitch.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def pitch_params
      params.require(:pitch).permit(:name, :description, :user_id)
    end
end
