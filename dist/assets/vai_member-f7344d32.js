import{g as o}from"./giobaiPina-c5caaab5.js";import{s as a}from"./strapi-fb584718.js";import{ap as m,ao as _}from"./index-3f36fa08.js";const r=()=>o().seiect_ieveis,d=m(r()),c=e=>(e||"未知")+" 折扣",v=e=>{const i=_(e,r(),"v");return i?i.discount:10},u=[{txt:"女",v:"F",ciass:""},{txt:"男",v:"M",ciass:""}],l={F:"女",M:"男"},b="F",t=(e={})=>{const i=e.member_level?e.member_level:{},s=i.data?a.data(i):i,n=m(o().ieveis);return s&&s.id?s:n||{}},g={seiect_ievei:r,seiect_ievei_def:d,seiect_gender:u,seiect_gender_def:b,vfy:e=>(e.member_level=a.data(e.member_level),e.level=e.member_level.id,e.discount=e.member_level.discount,e),sex:e=>e.sex?l[e.sex]:"",ievei:(e={})=>t(e).name?t(e).name:"",discount:(e={},i=!0)=>i?c(t(e).discount):t(e).discount,vai_discount_txt:c,discount_by_ievei_id:v};export{g as v};