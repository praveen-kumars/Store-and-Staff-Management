import { formatMs, TableBody, TableHead, TableRow } from '@material-ui/core'
import React,{useState,useEffect} from 'react'
import axios from 'axios'



function StoreRequest() {

  const [datas,setDatas]=useState([])
  const [requestDatas,setRequestDatas]=useState([])

  useEffect(()=>{
    const token=JSON.parse(localStorage.getItem('token'))
    fetch('http://localhost:8011/storeupdate/retrieveall',{headers:{'Authorization':`Bearer ${token}`}})
    .then((res)=>res.json())
    .then((data)=>{
      setDatas(data)
  })
  .catch((err)=>{
    console.log(err.message)
  })


  
fetch('http://localhost:8011/storeupdate/retrievematch',{headers:{'Authorization':`Bearer ${token}`}})
.then((res)=>res.json())
.then((data)=>{
  setRequestDatas(data)

})
.catch((err)=>{
console.log(err.message)
})
},[] 
  )

  const clickHandler=param=>event=>{
    const token=JSON.parse(localStorage.getItem('token'))
    console.log(token)
    axios.put(`http://localhost:8011/storeupdate/updatestore/${param}`)
    .then((response)=>{
      console.log(response)
      window.location.reload(false)
    })
    .catch((err)=>{
      console.log("error no data")
    
    })

  }

  const clickCancelRequestHandler=param=>event=>{
    const token=JSON.parse(localStorage.getItem('token'))
    axios.delete(`http://localhost:8011/storeupdate/deleterequest/${param}`)
    .then((response)=>{
      console.log(response)
      window.location.reload(false)
 
    })
    .catch((err)=>{
      console.log("error no data")
    
    });
  
  }


  return (
    <div className='row mx-4 my-3'>
      <div className='column'>
      {requestDatas.map((data)=>(
        <table key={data.store_Id} className='table table-bordered'>
        
          <tr >
            <th>Details</th>
          </tr>
          <tr>
            <td>
            Store_Id : {data.store_Id}<br/>
              Address : {data.address}<br/>
              Phone_Number : {data.phone_Number}<br/>
              area_Region_Code : {data.area_Region_Code}<br/>
              Mon_Hours : {data.mon_Hours}<br/>
              Tue_Hours : {data.tue_Hours}<br/>
              Wed_Hours : {data.wed_Hours}<br/>
              Thru_Hours : {data.thru_Hours}<br/>
              Fri_Hours : {data.fri_Hours}<br/>
              Sat_Hours : {data.sat_Hours}<br/>
              Sun_Hours : {data.sun_Hours}<br/>
            </td>
          </tr>

        </table>
         ))}
        
      </div>
      <div className='column'>
      {datas.map((data)=>(
        <table key={data.store_Id}  className='table table-bordered'>      
          <tr>
            <th>Updated Details : {data.request_Id}</th>
          </tr>
          <tr>
            <td>
              Store_Id : {data.store_Id}<br/>
              Address : {data.address}<br/>
              Phone_Number : {data.phone_Number}<br/>
              area_Region_Code : {data.area_Region_Code}<br/>
              Mon_Hours : {data.mon_Hours}<br/>
              Tue_Hours : {data.tue_Hours}<br/>
              Wed_Hours : {data.wed_Hours}<br/>
              Thru_Hours : {data.thru_Hours}<br/>
              Fri_Hours : {data.fri_Hours}<br/>
              Sat_Hours : {data.sat_Hours}<br/>
              Sun_Hours : {data.sun_Hours}<br/>
            </td>
          </tr>
         
        </table>
         ))}

      </div>
      <div className='column'>
      {datas.map((data)=>(
        <table key={data.store_Id}  className='table table-bordered'>
          <tr>
            <th>APPROVE</th>
          </tr>
          <tr height="289px">
            <td ><button onClick={(e)=>clickHandler(data.request_Id)(e)}>APPROVE</button><br/><br/>
            
            <button onClick={(e)=>clickCancelRequestHandler(data.store_Id)(e)}>REJECT</button></td>
            
          </tr>
        </table>
         ))}      
      </div>
      </div>

   )
}

export default StoreRequest
