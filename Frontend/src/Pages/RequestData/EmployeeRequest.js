import React from 'react'
import {useState,useEffect} from 'react'
import axios from 'axios'

function EmployeeRequest() {
  const [datas,setDatas]=useState([])
  const [requestDatas,setRequestDatas]=useState([])

  useEffect(()=>{
    const token=JSON.parse(localStorage.getItem('token'))
    fetch('http://localhost:8012/employeeupdate/retrieveall',{headers:{'Authorization':`Bearer ${token}`}})
    .then((res)=>res.json())
    .then((data)=>{
      setDatas(data)
  })
  .catch((err)=>{
    console.log(err.message)
  })


  
fetch('http://localhost:8012/employeeupdate/retrievematch',{headers:{'Authorization':`Bearer ${token}`}})
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
    axios.put(`http://localhost:8012/employeeupdate/updateemployee/${param}`)
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
      axios.delete(`http://localhost:8012/employeeupdate/deleterequest/${param}`)
      .then((response)=>{
        console.log(response)
        window.location.reload(false)
   
      })
      .catch((err)=>{
        console.log("error no data")
      
      });

  }



  return (
    <div className='row mx-5 my-3'>
      <div className='column'>
      {requestDatas.map((data)=>(
        <table key={data.staff_Id} className='table table-bordered'>
        
          <tr >
            <th>Details</th>
          </tr>
          <tr>
            <td>
            Staff Id :  { data.staff_Id}<br/>
            Position : {data.position}<br/>
            Email : {data.email}<br/>
            Office Phone Num : {data.office_Phone_Num}<br/>
            Phone Number : {data.phone_Number}<br/>
            dept_Area_Region_District : {data.dept_Area_Region_District}
            </td>
          </tr>

        </table>
         ))}
        
      </div>
      <div className='column'>
      {datas.map((data)=>(
        <table key={data.staff_Id}  className='table table-bordered'>      
          <tr>
            <th>Updated Details - {data.request_Id}</th>
          </tr>
          <tr>
            <td>
            Staff Id :  { data.staff_Id}<br/>
            Position : {data.position}<br/>
            Email : {data.email}<br/>
            Office Phone Num : {data.office_Phone_Num}<br/>
            Phone Number : {data.phone_Number}<br/>
            dept_Area_Region_District : {data.dept_Area_Region_District}
            </td>
          </tr>
         
        </table>
         ))}

      </div>
      <div className='column'>
      {datas.map((data)=>(
        <table key={data.staff_Id}  className='table table-bordered'>
          <tr>
            <th >APPROVE</th>
          </tr>
          <tr height="169px">
            <td><button onClick={(e)=>clickHandler(data.request_Id)(e)}>APPROVE</button><br/><br/>
            <button onClick={(e)=>clickCancelRequestHandler(data.staff_Id)(e)}>REJECT</button></td>
          </tr>
        </table>
         ))}      
      </div>
      </div>

   )
  
}

export default EmployeeRequest
