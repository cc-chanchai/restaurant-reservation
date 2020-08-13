import React, { Component } from 'react'
import ReservationServices from '../services/ReservationServices'

class Reservation extends Component {

  constructor(){
    super()
    this.state = {
      username:'we',
      userQueue: 0,
      currentQueue: 0,
      totalQueue: 0
    }

    this.findAllQueue = this.findAllQueue.bind(this)
    this.findUserQueue = this.findUserQueue.bind(this)
    this.findCurrentQueue = this.findCurrentQueue.bind(this)
    this.reservationFunction = this.reservationFunction.bind(this)
    this.nextQueue = this.nextQueue.bind(this)
  }
  

  componentDidMount(){
    this.findAllQueue()
    this.findCurrentQueue()
  }
  findAllQueue(){
    ReservationServices.getAll()
      .then(response => {
        this.setState({
          queues: response.data,
          totalQueue: response.data.length
        })
      })
      .then(
        this.findUserQueue,
        this.findCurrentQueue
      ).catch(e => {
        console.log(e);
      })
  }
  findUserQueue(){
    this.state.queues.forEach(userQueue => {
      if(userQueue.username === this.state.username){
        this.setState({
          userQueue: userQueue.queue.id,
        })
      }
    });
  }

  findCurrentQueue(){
    ReservationServices.getCurrentQueue()
      .then(res => {
        this.setState({ currentQueue: res.data })
      }).catch(error => {
        console.log(error);
      })
  }

  reservationFunction(){
    ReservationServices.createQueue(this.state.username)
    .then(res => {
      this.setState({
        userQueue: res.data.id,
        totalQueue: res.data.length,
      })
    }).catch(error => {
      console.log(error);
    })
  }

  nextQueue(){
    ReservationServices.nextQueueService(this.state.currentQueue)
  }


  render() {
    let waiting = (this.state.userQueue) - (this.state.currentQueue)
    return (
      <div>
        <div className="container mt-4" style={{width: "400px"}}>
        <div className="card mb-2">
          <div className="card-header">
            <h4>ยินดีต้อนรับ { this.state.username }</h4>
          </div>
          <div className="card-body">
            <p>คิวทั้งหมด : { this.state.totalQueue }</p> {/*hidden={this.state.userQueue > 0}*/}
            <p>คิวปัจจุบัน : { this.state.currentQueue }</p>
            <p>คิวของท่าน : { this.state.userQueue === 0 ? 'ท่านยังไม่ได้ทำการจองคิว' : this.state.userQueue }</p>
            <p>เหลืออีก : { waiting === 0 ? "ถึงคิวท่านแล้ว" : waiting < 0 ? 'ท่านยังไม่ได้ทำการจองคิว/คิวของท่านผ่านไปแล้ว' : waiting}</p>
          </div>
        </div>
        <div>
          <button className="btn btn-warning" disabled={this.state.userQueue > 0} onClick={this.reservationFunction}>จองคิว</button>
        </div>
      </div>
      <div className="container mt-4" style={{width: "400px"}}>
        <button className="btn btn-success" onClick={this.nextQueue}>เรียกคิวถัดไป</button>
      </div>
    </div>
    )
  }
}

export default Reservation
