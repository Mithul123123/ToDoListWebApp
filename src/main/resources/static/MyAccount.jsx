import styles from './MyAccount.module.css'
import Header from './Header';
import user from './assets/pictures/145857007_307ce493-b254-4b2d-8ba4-d12c080d6651.jpg';
import { useContext } from 'react';
import { Storage } from './Storage';
import { useRef } from 'react';
function MyAccount()
{ 
   
     const {TDate,setDate,Time,setTime,Task,setTask,TaskNum,setTaskNum,rows,setRows,newarray,date,time,task,num,string,setString,count,setcount}=useContext(Storage)

return (<>
<Header></Header>
<div className={styles.body}>
    <div className={styles.form}>
        <div className={styles.imgbox}><img className={styles.img}src={user}/></div>
        <label className={styles.label}>user</label>
        <button onClick={()=>SignInClick(string,setString,count,setcount)}className={styles.signin}>Sign In</button>
        <button onClick={()=>SignOutClick(string,setString,setRows,rows,count,setcount)}className={styles.signout}>Sign out</button>
    </div>
</div>
</>);
}
export default MyAccount
function SignInClick(string,setString,count,setcount)
{
    setcount((count)=>count+1);
    console.log(count)
    if( count>=0)
    {
        setString("");
    }
console.log("Sign in clicked");
}
function SignOutClick(string,setString,setRows,rows,count,setcount)
{
    setcount(()=>0);
     setRows(rows.filter(row => row.id !== row.id));
    console.log("Sign out clicked");
    setString(()=>"Please Sign In Before Using!")
}