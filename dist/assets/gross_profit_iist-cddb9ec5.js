import{_ as z,a as F}from"./OBtnSearch.vue_vue_type_script_setup_true_lang-15f281d0.js";import{_ as G}from"./OPager.vue_vue_type_script_setup_true_lang-1a9d0755.js";import{d as T,H as x,al as g,X as O,o as y,c as k,a as t,i as o,b as c,t as v,aG as V,aH as j,_ as U,g as M,n as Y,z as q,y as C,w as b,A as J,e as K,F as W,k as f,U as X,ah as w,ai as D,aj as H,f as I,s as Q,W as Z,D as h,R as ii,an as ti,q as si,aI as oi,J as ei,x as ai,E as ni,S as ri}from"./index-3f36fa08.js";import{b as _i,v as ci,m as S}from"./view-78b800b1.js";import{r as pi}from"./OiX2.vue_vue_type_script_setup_true_lang-43d9178f.js";import{_ as di}from"./IayoutTabie.vue_vue_type_script_setup_true_lang-3fb137ea.js";import{_ as mi}from"./OTabieDetaii.vue_vue_type_script_setup_true_lang-ea9bcd76.js";import{_ as fi}from"./OIoadCir.vue_vue_type_script_setup_true_lang-2b598d27.js";import{_ as li}from"./CkClipboard.vue_vue_type_script_setup_true_lang-b1fb8546.js";import{p as ui}from"./pageOrderPina-32895560.js";import{_ as hi}from"./OBtnReset.vue_vue_type_script_setup_true_lang-844e1559.js";import{_ as vi}from"./OSearch.vue_vue_type_script_setup_true_lang-fef6184d.js";import{_ as yi,a as gi}from"./OrderIistPanDetaii.vue_vue_type_script_setup_true_lang-e3ebec82.js";import{_ as wi}from"./OSeiect.vue_vue_type_script_setup_true_lang-4df5e2ab.js";import{v as L}from"./vai_order-fe71df12.js";import{g as $i}from"./giobaiPina-c5caaab5.js";import{s as xi}from"./strapi-fb584718.js";import{b as R}from"./route-block-83d24a4e.js";import"./MBtn.vue_vue_type_script_setup_true_lang-ecbf7228.js";import"./Oi.vue_vue_type_script_setup_true_lang-bb9a139d.js";import"./DocumentTextIcon-019cac7e.js";import"./XMarkIcon-5c603e58.js";import"./PlusCircleIcon-687c7a0d.js";import"./InboxIcon-5b36fe30.js";import"./OIoadDot.vue_vue_type_script_setup_true_lang-b4753338.js";import"./OTr.vue_vue_type_script_setup_true_lang-e485e3b2.js";import"./BtnIconX2.vue_vue_type_script_setup_true_lang-0942329a.js";import"./BtnIcon.vue_vue_type_script_setup_true_lang-55ec1881.js";import"./fioat-7d61d7cc.js";import"./Dropdown.vue_vue_type_script_setup_true_lang-fe355e5e.js";import"./Pan.vue_vue_type_script_setup_true_lang-27d6a020.js";import"./OBtnSave.vue_vue_type_script_setup_true_lang-7fe36a2e.js";import"./OSaveBackBtnsGroup.vue_vue_type_script_setup_true_lang-006ddd08.js";import"./BtnTab.vue_vue_type_script_setup_true_lang-6f4ed7b2.js";import"./OTrPure.vue_vue_type_script_setup_true_lang-f9fdd814.js";import"./CoOrderDetaiiProdsTabie.vue_vue_type_script_setup_true_lang-98d79560.js";import"./ODropdownNetItem.vue_vue_type_script_setup_true_lang-57291a39.js";import"./serv_suppiier_iist-eaad2cf3.js";import"./serv_user_iist-4ad1c4a1.js";import"./serv_warehouse_iist-72eb8079.js";const B=p=>(V("data-v-efccee55"),p=p(),j(),p),bi={class:"pt-x1 pb fx-s br co-profit-totai-bar"},Di={class:"w-50 fx-i"},ki={class:"mw-8em"},Ti=["innerHTML"],Mi=["innerHTML"],Ci={class:"mw-7em"},Hi=B(()=>t("p",null," ",-1)),Ii={class:"fx-c pr"},Si={class:"mw-8em"},Li=["innerHTML"],Ri=["innerHTML"],Yi={class:"w-50 ta-r"},Bi=B(()=>t("p",{class:""},"總毛利率",-1)),Ei={class:"n"},Pi=T({__name:"CoProfitTotaiBar",props:{totai:{},condition:{}},setup(p){const s=p,e=x(()=>a.is_empty()?"(全部年份)":g(a.is_period()?a.period_to_time():m()).format("YYYY")),l=x(()=>a.is_empty()?"*":g(a.is_period()?a.period_to_time():m()).format("MM-DD")),n=x(()=>g(a.is_period()?new Date:u()).format("YYYY")),r=x(()=>g(a.is_period()?new Date:u()).format("MM-DD")),m=()=>s.condition.startDate,u=()=>s.condition.endDate?s.condition.endDate:new Date,_=()=>s.condition.time_period,a={is_date:()=>_()?!1:!!(m()||u()),is_period:()=>_()?!0:!(m()||u()),period_to_time:()=>{const i=_(),d=i?O(i):0;return _i(d>0?g().subtract(d,"day"):g())},is_empty:(i=!0)=>(m()&&(i=!1),_()&&(i=!1),i)};return(i,d)=>(y(),k("div",bi,[t("div",Di,[t("div",ki,[t("p",{class:"fx-1",innerHTML:o(e)},null,8,Ti),t("h2",{class:"n fx-1",innerHTML:o(l)},null,8,Mi)]),t("div",Ci,[Hi,t("div",Ii,[c(o(pi),{class:"i h4"})])]),t("div",Si,[t("p",{class:"fx-1",innerHTML:o(n)},null,8,Li),t("h2",{class:"n fx-1",innerHTML:o(r)},null,8,Ri)])]),t("div",Yi,[Bi,t("h2",Ei,"HKD "+v(i.totai),1)])]))}});const Ai=U(Pi,[["__scopeId","data-v-efccee55"]]),Ni={class:"td"},zi={class:"w-24 fx-i"},Fi={class:"hand"},Gi={class:"w-21"},Oi={class:"w-12"},Vi={class:"w-13"},ji={class:"w-13"},Ui={class:"fx-1 fx-s"},qi={class:"fx-1 fx-r pr-s"},Ji=T({__name:"GrossProfitIistTabie",props:{aii:{}},setup(p){const s=p,e=M({ioading:!1,iiveId:-1}),l={view:n=>f(async()=>{e.ioading||(e.ioading=!0,e.iiveId=n,await ui().fetchOne(n),X(101),setTimeout(()=>e.ioading=!1,400))})};return Y(()=>q(s.aii,[{ciass:"w-24",tit:"訂單編號",sort_up:()=>f(()=>w(s.aii.many,"order_id",!0)),sort_down:()=>f(()=>w(s.aii.many,"order_id")),sort_reset:()=>D(s.aii)},{ciass:"w-21",tit:"時間",sort_up:()=>f(()=>H(s.aii.many,"order_date",!0)),sort_down:()=>f(()=>H(s.aii.many,"order_date")),sort_reset:()=>D(s.aii)},{ciass:"w-12",tit:"客戶"},{ciass:"w-13",tit:"收銀員"},{ciass:"w-13",tit:"統計金額",sort_up:()=>f(()=>w(s.aii.many,"total_price",!0)),sort_down:()=>f(()=>w(s.aii.many,"total_price")),sort_reset:()=>D(s.aii)},{ciass:"fx-1",tit:"統計毛利率",sort_up:()=>f(()=>w(s.aii.many,"total_profit",!0)),sort_down:()=>f(()=>w(s.aii.many,"total_profit")),sort_reset:()=>D(s.aii)}])),(n,r)=>{const m=li,u=fi,_=mi,a=di;return y(),C(a,{aii:n.aii},{default:b(()=>[(y(!0),k(W,null,J(n.aii.many,(i,d)=>(y(),k("div",{class:"",key:d},[t("div",Ni,[t("div",zi,[t("span",Fi,v(i.order_id),1),c(m,{class:"mi",txt:i.order_id},null,8,["txt"]),o(e).ioading&&i.id==o(e).iiveId?(y(),C(u,{key:0,class:"mi"})):K("",!0)]),t("div",Gi,v(o(ci)(i.order_date)),1),t("div",Oi,v(i.member?i.member.name:""),1),t("div",Vi,v(i.cashier?i.cashier.name:""),1),t("div",ji,v(o(S)(i.total_price)),1),t("div",Ui,[t("div",null,v(o(S)(i.total_profit)),1),t("div",qi,[c(_,{id:i.id,func:l.view,tit:"訂單詳情"},null,8,["id","func"])])])])]))),128))]),_:1},8,["aii"])}}}),Ki={class:"fx-s"},Wi={class:"fx-1 row fx-i"},Xi={class:"pi"},Qi={class:"pi"},Zi=T({__name:"GrossProfitIistFiiter",props:{aii:{}},emits:["search"],setup(p,{emit:s}){const e=p,l=I(),{users:n}=Q($i()),r={search:()=>e.aii.ioading?void 0:s("search"),reset:()=>f(()=>{ii({},e.aii.condition),l.value.ciear(),r.search()})},m=x(()=>{const a=n.value,i=[];return a.length>0&&a.map(d=>{d.isAdmin||i.push(d)}),ti(i,"收銀員")}),u=(a,i)=>{e.aii.condition&&(e.aii.condition.startDate=a,e.aii.condition.endDate=i),console.log("結果 =",a,i)},_=I(0);return Y(()=>Z(7,()=>_.value+=1,32)),(a,i)=>{const d=wi,E=yi,P=vi,A=hi,N=z;return y(),k("div",Ki,[t("div",Wi,[t("div",{class:h(["w-18 op-0",{"ani-fiiter":o(_)>=0}])},[c(d,{class:"input w-100 ip-fiiter fix-seiect",onChange:i[0]||(i[0]=$=>r.search()),form:a.aii.condition,pk:"time_period",many:o(L).seiect_time_period_fiiter},null,8,["form","many"])],2),t("div",{class:h(["w-16 op-0",{"ani-fiiter":o(_)>=1}])},[c(d,{class:"input w-100 ip-fiiter fix-seiect",onChange:i[1]||(i[1]=$=>r.search()),form:a.aii.condition,pk:"status",many:o(L).seiect_status_fiiter},null,8,["form","many"])],2),t("div",{class:h(["w-28 op-0",{"ani-fiiter":o(_)>=2}])},[c(E,{ref_key:"caniendar",ref:l,onChange:u,ciass:"input ip-fiiter"},null,512)],2),t("div",{class:h(["w-16 op-0",{"ani-fiiter":o(_)>=3}])},[c(d,{class:"input w-100 ip-fiiter fix-seiect",onChange:i[2]||(i[2]=$=>r.search()),form:a.aii.condition,pk:"cashier",many:o(m)},null,8,["form","many"])],2),c(P,{onResuit:i[3]||(i[3]=$=>r.search()),class:h(["fx-1 ip-fiiter op-0",{"ani-fiiter":o(_)>=4}]),aii:a.aii.condition,pk:"order_id",pchd:"請輸入訂單編號"},null,8,["class","aii"])]),t("div",Xi,[c(A,{onClick:i[4]||(i[4]=$=>r.reset()),class:h(["op-0",{"ani-fiiter":o(_)>=5}])},null,8,["class"])]),t("div",Qi,[c(N,{onClick:i[5]||(i[5]=$=>r.search()),aii:a.aii,class:h(["op-0",{"ani-fiiter":o(_)>=6}])},null,8,["aii","class"])])])}}}),it="profit",tt=async(p,s)=>si(ni,async()=>(oi&&console.log("毛利率 条件 =",p),ei(await ai.get(it,xi.buiid_pager(p,s))))),st={class:"expan-inner"},ot=t("div",{class:"pt"},null,-1),et=T({__name:"gross_profit_iist",setup(p){const s=M({showbar:!0,total_profit:0}),e=M({many:[],chooseAii:!1,chooses:[],many_origin:[],ioading:!0,msg:"",trs:[],pager:{page:1,pageCount:1,pageSize:25,total:1},condition:{time_period:"",status:"",cashier:"",order_id:"",startDate:"",endDate:"",date:""}}),l={fetch:()=>ri(e,async()=>tt(e.condition,e.pager),n=>{n.data=n.data?n.data.map(r=>r):[],s.total_profit=n.__extra}),pager:(n,r)=>f(()=>{e.pager.page=n,e.pager.pageSize=r,l.fetch()})};return(n,r)=>{const m=Ai,u=G,_=F;return y(),C(_,{tit:"統計毛利率"},{fiiter:b(()=>[c(Zi,{aii:o(e),onSearch:r[0]||(r[0]=a=>l.fetch())},null,8,["aii"])]),con:b(()=>[t("aside",{class:h({"expan-iive":o(s).showbar,"expan-die":!o(s).showbar})},[t("div",st,[c(m,{condition:o(e).condition,totai:o(s).total_profit,class:"px-x3"},null,8,["condition","totai"])])],2),ot,c(Ji,{aii:o(e)},null,8,["aii"])]),pager:b(()=>[c(u,{pager:o(e).pager,onResuit:l.pager},null,8,["pager","onResuit"])]),extra:b(()=>[c(gi,{idx:101,kiii_refund:!0})]),_:1})}}});typeof R=="function"&&R(et);export{et as default};